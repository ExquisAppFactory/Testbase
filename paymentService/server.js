const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
const connectDB = require('./src/config/db');
const paymentRoute = require('./src/routes/payment.route');
require("dotenv").config();

const app = express();

//middlewares
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(cors());
app.use(express.json());

// use routes
app.use('/api', paymentRoute);

app.get("/", (req, res) => {
  res.send(`<h1>Welcome to the ExquisApp Testbase Payment Service <br> part of the Moneypal Application</h1>
    <h4>Please use PostMan and navigate to <code>/api/v1</code> to use the app</h4>
    <h4>Thanks  &#x1F600;</h4>`);
});

// connect DB here
connectDB();

//routes
app.listen(process.env.PORT, () =>
  console.log(`server running on port ${process.env.PORT}`)
);

module.exports = app;
