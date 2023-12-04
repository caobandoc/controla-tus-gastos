import "./home.css";

//mui
import Button from "@mui/material/Button";
import { useTheme } from "@mui/material/styles";
import PersonIcon from "@mui/icons-material/Person";

//router
import { Link } from "react-router-dom";

const Home: React.FC = () => {
    const theme = useTheme();
    const primaryColor = theme.palette.primary.main;
    return (
        <nav style={{ backgroundColor: primaryColor }}>
            <span>Controla tus gastos</span>
            <span className="spacer"></span>
            <Link to={"/login"}>
                <Button
                    variant="outlined"
                    startIcon={<PersonIcon color="primary" />}
                    style={{ backgroundColor: "#fff" }}
                >
                    Ingresar
                </Button>
            </Link>
        </nav>
    );
};

export default Home;
