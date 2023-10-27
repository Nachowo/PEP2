import axios from 'axios';

const ALUMNO_API_URL = "http://localhost:8091/alumno";

class AlumnoService {

    getAlumnos(){
        return axios.get(ALUMNO_API_URL);
    }

    crearAlumno(alumno){
        return axios.post(ALUMNO_API_URL, alumno);
    }

    getAlumnoById(id){
        return axios.get(ALUMNO_API_URL + '/' + id);
    }
}

export default new AlumnoService()