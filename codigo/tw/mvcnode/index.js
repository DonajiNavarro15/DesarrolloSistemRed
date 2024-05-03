const express = require('express');
const dotenv = require('dotenv');
const app = express();

dotenv.config();

app.set('view engine', 'ejs');

const homeRouter = require('./routes/home');
app.use('/', homeRouter);
app.use('/home', homeRouter);

const errorHandler = require('./middlewares/errorHandler');
app.use(errorHandler);

app.listen(process.env.SERVER_PORT, () => {
    console.log(`Aplicación corriendo en http://localhost:${process.env.SERVER_PORT}`);
});
