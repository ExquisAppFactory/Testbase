const mongoose = require("mongoose");
const jwt = require("jsonwebtoken");

//Create user schema
const userSchema = mongoose.Schema({
  first_name: {
    type: String,
    required: true,
    min: 3,
    max: 60,
  },
  last_name: {
    type: String,
    required: true,
    min: 3,
    max: 60,
  },
  username: {
    type: String,
    required: true,
    unique: true,
    min: 3,
    max: 60,
  },
  password: {
    type: String,
    required: true,
    min: 8,
    max: 115,
    select: false,
  },
  status: {
    type: String, 
    enum: ['Pending', 'Active'],
    default: 'Pending'
  },
  confirmationCode: { 
    type: String, 
    unique: true 
  },
});

userSchema.methods.getSignedToken = function () {
  return jwt.sign(
    { _id: this._id, username: this.username },
    process.env.TOKEN_SECRET_KEY,
    { expiresIn: '2h' }
  );
};

module.exports = mongoose.model("Users", userSchema);
