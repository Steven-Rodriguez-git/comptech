import CardsComponents from "../../components/CardsComponents.js";
import Filtros from "../../components/Filtros.jsx";

import Grid from '@mui/material/Grid';



export default function AllComponents (){
return(
    // <ThemeProvider theme={theme}>


  <Grid container spacing={2} fluid style={{ padding: "40px", backgroundColor: "#A23E9B"}}>
  <Grid item xs={4} fluid style={{ padding: "80px", backgroundColor: "#3EA270"}}>
    <Filtros></Filtros>
  </Grid>
  <Grid item xs={7}>
    <CardsComponents/>
  </Grid>


  {/* <Grid item xs={3}>
     <Item>xs=4</Item> 
  </Grid>
  <Grid item xs={9}>
  </Grid> */}
</Grid>
//</ThemeProvider>

);
};