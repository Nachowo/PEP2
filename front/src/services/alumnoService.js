import axios from 'axios';

const ALUMNO_API_URL = "http://gateway-service/alumno";

const errorHandler = (error) => {
    if(error.request){
        console.log("Error request");
    }else if(error.response){
        console.log("Error en");
    } 
    return Promise.reject(error);
}

const alumnoService = axios.create({
    baseURL: ALUMNO_API_URL,
});

alumnoService.interceptors.response.use(undefined, errorHandler);
class AlumnoService {

    async getAlumnos(){
        const res = await alumnoService.get('/');
        return res.data.alumnos;
    }

    crearAlumno(alumno){
        return alumnoService.post('/', alumno);
    }

    getAlumnoById(id){
        return alumnoService.get( '/'+id);
    }

    async getReporte(){
        const res = await alumnoService.get('/reporte');
        return res.data;
    }
}


export default new AlumnoService()