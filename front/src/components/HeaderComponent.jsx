import React from 'react';
import {useNavigate } from 'react-router-dom';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';


const HeaderComponent = () => {

  return (
    <div>
      <header>
        <nav className="navbar navbar-expand-md navbar-dark bg-dark">
          <div>
            <ul className="navbar-nav mr-auto">
            <ButtonGroup variant="contained" aria-label="outlined primary button group">
                <Button variant='contained' color='primary' href='/'>Principal</Button>
                <Button variant='contained' color='primary'href='/crear-alumno'>Crear Alumno</Button>
                <Button variant='contained' color='primary'href='/listar-alumnos'>Lista Alumnos</Button>
            </ButtonGroup>
                
                

            </ul>
          </div>
         
        </nav>
      </header>
    </div>
  );
};

export default HeaderComponent;
