import React, { useState } from 'react';
import alumnoService from '../services/alumnoService';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import { useNavigate } from 'react-router-dom';

const CrearAlumno = ({ history }) => {
  const navigate = useNavigate();
  const [state, setState] = useState({
    rut: '',
    nombre: '',
    apellido: '',
    nacimiento: '',
    tipoColegio: '',
    nombreColegio: '',
    anoEgreso: '',
    tipoPago: '',
    cantidadCuotas: '',
    cuota: true,
    maxCuotas: 0
  });

  const rutHandler = (event) => {
    setState({ ...state, rut: event.target.value });
  };

  const nombreHandler = (event) => {
    setState({ ...state, nombre: event.target.value });
  };

  const apellidoHandler = (event) => {
    setState({ ...state, apellido: event.target.value });
  };

  const nacimientoHandler = (event) => {
    setState({ ...state, nacimiento: event.target.value });
  };

  const tColegioHandler = (event) => {
    let maxCuotas = 0;
    if (event.target.value === '3') {
      maxCuotas = 10;
    } else if (event.target.value === '2') {
      maxCuotas = 4;
    } else {
      maxCuotas = 7;
    }
    setState({ ...state, tipoColegio: event.target.value, maxCuotas });
  };

  const nColegioHandler = (event) => {
    setState({ ...state, nombreColegio: event.target.value });
  };

  const egresoHandler = (event) => {
    setState({ ...state, anoEgreso: event.target.value });
  };

  const tPagoHandler = (event) => {
    const cuota = event.target.value === '2' ? false : true;
    setState({ ...state, cuota, tipoPago: event.target.value });
  };

  const cCuotasHandler = (event) => {
    const cant = event.target.value;
    const cant2 = cant > state.maxCuotas ? state.maxCuotas : cant;
    setState({ ...state, cantidadCuotas: cant2 });
  };

  const saveAlumno = (e) => {
    e.preventDefault();
    let alumno = {
      rut: state.rut,
      nombre1: state.nombre,
      apellido: state.apellido,
      nacimiento: state.nacimiento,
      tipoColegio: state.tipoColegio,
      nombreColegio: state.nombreColegio,
      anoEgreso: state.anoEgreso,
      tipoPago: state.tipoPago,
      cantidadCuotas: state.cantidadCuotas
    };

    alumnoService.crearAlumno(alumno)
      .then(res => {
        console.log(res.data);
        navigate('/listar-alumnos');
      })
      .catch(err => console.log(err));
  };

  const cancel = () => {
    history.push('/');
  };

  return (
    <div>
      <br></br>
      <div className="container">
        <div className="row">
          <div className="card col-md-6 offset-md-3 offset-md-3">
            <h3 className="text-center">Add Student</h3>
            <div className="card-body">
              <Box
                component="form"
                sx={{
                  '& .MuiTextField-root': { m: 1, width: '25ch' },
                }}
                noValidate
                autoComplete="off"
              >
                {/*Campo para ingresar el rut */}
            <div className="form-group">
                <TextField
                  required
                  id="rut"
                  label="Rut"
                  value={state.rut} onChange={rutHandler}
                />
            </div>

            {/*Campo para ingresar el nombre */}
            <div className="form-group">
              <label> Nombre: </label>
              <input placeholder="Ingresar el nombre" name="nombre" className="form-control" 
                value={state.nombre} onChange={nombreHandler}/>
            </div>

            {/*Campo para ingresar el apellido */}
            <div className="form-group">
              <label> Apellido: </label>
              <input placeholder="Ingresar el apellido" name="apellido" className="form-control" 
                value={state.apellido} onChange={apellidoHandler}/>
            </div>

            {/*Campo para ingresar la fecha de nacimiento */}
            <div className="form-group">
              <label> Fecha de nacimiento: </label>
              <input placeholder="Ingresar fecha de nacimiento" name="nacimiento" className="form-control" type="date"
                value={state.nacimiento} onChange={nacimientoHandler}/>
            </div>

            {/*Campo para ingresar el tipo de colegio */}
            <div className="form-group">
              <label> Tipo de colegio: </label>
              <select placeholder="Seleccione el tipo de colegio" name="tipoColegio" className="form-control" 
              value={state.tipoColegio} onChange={tColegioHandler}>
                <option value="3">Municipal</option>
                <option value="2">Privado</option>
                <option value="1">Subvencionado</option>
              </select>
            </div>

            {/*Campo para ingresar el nombre del colegio */}
            <div className="form-group">
              <label> Nombre del colegio: </label>
              <input placeholder="Ingresar nombre del colegio" name="nombreColegio" className="form-control" 
                value={state.nombreColegio} onChange={nColegioHandler}/>
            </div>

            {/*Campo para ingresar el año de egreso */}
            <div className="form-group">
              <label> Año de egreso: </label>
              <input placeholder="Ingresar año de egreso" name="anoEgreso" className="form-control" 
                value={state.anoEgreso} onChange={egresoHandler}/>
            </div>

            {/*Campo para ingresar el tipo de pago */}
            <div className="form-group">
              <label> Tipo de pago: </label>
              <select name="tipoPago" className="form-control" 
              value={state.tipoPago} onChange={tPagoHandler}>
                <option value="1">Al contado</option>
                <option value="2">Cuotas</option>
              </select>
            </div>

            {/*Campo para ingresar la cantidad de cuotas */}
            {!state.cuota && <div className="form-group">
              <label> Cantidad de cuotas: </label>
              <input type='number' placeholder="Ingresar cantidad de cuotas" name="cantidadCuotas" className="form-control" 
                value={state.cantidadCuotas} onChange={cCuotasHandler} disabled={state.cuota}/>
            </div>}
                <Button variant="contained" onClick={saveAlumno} endIcon={<SendIcon />}>Guardar</Button>
                <button className="btn btn-danger" onClick={cancel} style={{ marginLeft: "10px" }}>Cancel</button>
              </Box>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CrearAlumno;
