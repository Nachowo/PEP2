import axios from 'axios';

const CUOTA_API_URL = "http://cuotas-service:8080/cuota";

const errorHandler = (error) => {
    if(error.request){
        console.log("Error request");
    }else if(error.response){
        console.log("Error en");
    } 
    return Promise.reject(error);
}

const cuotaService = axios.create({
    baseURL: CUOTA_API_URL,
});

cuotaService.interceptors.response.use(undefined, errorHandler);
class CuotaService {
    async getCuotasByAlumno(id){
        const res = await cuotaService.get('/alumno/'+id);
        console.log(res);
        return res;
    }

    async pagarCuota(id){
        const res = await cuotaService.put('/pagarCuota/'+id);
        return res.data;
    }

}

export default new CuotaService()