import Computadoras from "../../components/Computadoras";
import Titulos from "./Titulos";
import Ayudas from "./Ayudas";
import ImagenAyuda from "./ImagenAyuda";


import Box from '@mui/material/Box';

export default function Help (){
return(
    <div>
<Titulos></Titulos>

    <Box
        sx={{
        width: '100%',
        height: '7%',
        display: 'flex',
        backgroundColor: '#455a64',
        alignItems:"center"
        }}>
        <Box>
      <ImagenAyuda></ImagenAyuda>
        </Box>
        <Box sx={{
        width: '100%',
        height: '100%',
        display: 'flex',
        justifyContent:"flex-end",
        }}>
      <Ayudas ></Ayudas>
    </Box>
  </Box>  
  
    
    
<Computadoras/>
    </div>
);
};
