const User = require("../../models/user.model");
const { registerValidator } = require("../../utils/validation");
const encryptPassword = require("../../utils/encryptPassword");

const handleValidation = (body, key) => {
  const { error } = registerValidator(body);
  if (error) {
    throw Error(error.details[0].message);
  }
};

const registerController = async (req, res) => {
  try {
    const { username, password, firstName, lastName } = req.body;
    handleValidation({
      first_name: firstName,
      last_name: lastName,
      username: username,
      password: password
    }, "register");

    const usernameExist = await User.findOne({ username });
    console.log({ username });
    if (usernameExist) {
      return res.status(400).json({ 
        message: "Username already exists",
        isSuccessful: false,
        data: undefined
      });
    }
    req.body.password = await encryptPassword(password);
    const user = new User({
      first_name: firstName,
      last_name: lastName,
      username: username,
      password: password
    });
    console.log({ user })
    await user.save();

    return res.status(201).json({
      message: 'User signup successful',
      isSuccessful: true,
      data: null
    });
  } catch (error) {
    return res.status(400).json({ 
      message: error.message,
      isSuccessful: false,
      data: undefined
    });
  }
};

module.exports = registerController;
