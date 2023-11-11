import axios from 'axios';

const NOTA_API_URL = "http://localhost:8080/nota";

const errorHandler = (error) => {
    if(error.request){
        console.log("Error request");
    }else if(error.response){
    } 
    return Promise.reject(error);
}

const notaService = axios.create({
    baseURL: NOTA_API_URL,
});

notaService.interceptors.response.use(undefined, errorHandler);
class NotaService {
    async subirNotas(archivo){
        const res = await notaService.post('/subir', archivo);
        return res;
    }

}

export default new NotaService()