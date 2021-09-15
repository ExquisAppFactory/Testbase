
const nodemailer = require("nodemailer");
require('dotenv').config();

const transport = nodemailer.createTransport({
  service: "Gmail",
  auth: {
    user: process.env.MAIL_USERNAME,
    pass: process.env.MAIL_PASSWORD,
  },
});

module.exports.sendConfirmationEmail = (email, confirmationCode) => {
    console.log("Check");
    transport.sendMail({
      from: process.env.MAIL_USERNAME,
      to: email,
      subject: "Please confirm your account",
      html: `<h1>Email Confirmation</h1>
          <h2>Hello ${email.split('@')[0].trim()}</h2>
          <p>Thank you for subscribing. Please confirm your email by clicking on the following link</p>
          <a href=http://localhost:3000/confirm/${confirmationCode}> Click here</a>
          </div>`,
    }).catch(err => console.log(err));
  };