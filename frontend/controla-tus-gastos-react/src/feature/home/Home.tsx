import "./home.css";

//mui
import Button from "@mui/material/Button";
import PersonIcon from "@mui/icons-material/Person";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";

//router
import { Link } from "react-router-dom";

const Home: React.FC = () => {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <Typography
                        variant="h6"
                        component="div"
                        sx={{ flexGrow: 1 }}
                    >
                        Controla tus gastos
                    </Typography>
                    <Link to={"/login"}>
                        <Button
                            variant="outlined"
                            startIcon={<PersonIcon color="primary" />}
                            style={{ backgroundColor: "#fff" }}
                        >
                            Ingresar
                        </Button>
                    </Link>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Home;
