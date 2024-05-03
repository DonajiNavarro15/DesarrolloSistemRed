const errorHandler = (err, req, res, next) => {
    console.log('Manejo de errores desde un middleware');
    const defaultMensaje = 'La aplicación está ocupada. Inténtalo nuevamente más tarde.';

    if(process.env.NODE_ENV === 'development') {
        const statusCode = err.statusCode || 500;
        const message = err.message || defaultMensaje;

        res.status(statusCode).json({
            success: false,
            status: statusCode,
            message,
            stack: err.stack
        });
    } else {
        res.status(200).send(defaultMensaje);
    }
};

module.exports = errorHandler;