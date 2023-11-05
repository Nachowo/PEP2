import React from 'react';
import { useNavigate } from 'react-router-dom';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';

const HeaderComponent = () => {
  return (
    <div>
      <header>
        <nav style={{ display: 'flex', justifyContent: 'center' }}>
          <div>
            <ul style={{ listStyle: 'none', padding: 0 }}>
              <ButtonGroup
                variant="contained"
                aria-label="outlined primary button group"
                style={{ display: 'flex', justifyContent: 'center' }}
              >
                <Button variant="contained" color="primary" href="/">
                  Principal
                </Button>
                <Button variant="contained" color="primary" href="/crear-alumno">
                  Crear Alumno
                </Button>
                <Button variant="contained" color="primary" href="/listar-alumnos">
                  Lista Alumnos
                </Button>
                <Button variant="contained" color="primary" href="/ver-rerporte">
                  Ver Reporte
                </Button>
              </ButtonGroup>
            </ul>
          </div>
        </nav>
      </header>
    </div>
  );
};

export default HeaderComponent;
