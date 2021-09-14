const User = require("../../models/user.model");
const { loginValidator } = require("../../utils/validation");
const getUser = require("../../services/user.services");
const jwt = require('jsonwebtoken');
const bcrypt = require("bcryptjs");

const handleValidation = (body) => {
  const { error } = loginValidator(body);
  if (error) {
    throw Error(error.details[0].message);
  }
};

const loginController = async (req, res) => {
  try {
    handleValidation(req.body);

    const { username, password } = req.body;
    const user = await getUser({ username }, true);

    const validPassword = await bcrypt.compare(password, user.password);
    if (!validPassword) {
      return res.status(400).json({ err_msg: "incorrect password" });
    }
    const token = jwt.sign(
      { userId: user._id, email: user.username },
      process.env.TOKEN_KEY,
      {
        expiresIn: "2h",
      }
    );
    
    // const token = user.getSignedToken();
    return res.status(200).json({
      message: 'Login Successful',
      isSuccessful: true,
      data: {
        id: user._id,
        username: user.username,
        token,
        firstName: user.first_name,
        lastName: user.last_name
      }
    });
  } catch (error) {
    return res.status(401).json({ err_msg: error.message });
  }
};

module.exports = loginController;
