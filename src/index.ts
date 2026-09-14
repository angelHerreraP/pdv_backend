import express from 'express';

const app = express();
const PORT = 3000;

app.get('/', (req, res) => {
    res.send('PDV backend funcionando 🎉');
});

app.listen(PORT,() => {
    console.log(`Servidor funcionando en el puerto ${PORT}`);
});