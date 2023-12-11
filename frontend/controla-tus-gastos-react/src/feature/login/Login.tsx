import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./login.css";

//mui
import { Button, useTheme } from "@mui/material";
import TextField from "@mui/material/TextField";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert, { AlertProps } from "@mui/material/Alert";

//services
import apiService from "../../core/services/apiService";
import { validToken, setToken } from "../../core/services/tokenService";

const Login: React.FC = () => {
    //variables
    const theme = useTheme();
    const primaryColor = theme.palette.primary.main;

    //navigate
    const navigate = useNavigate();

    //hooks
    const [usuario, setUsuario] = useState<string | null>(null);
    const [contraseña, setContraseña] = useState<string | null>(null);
    const [isSubmitDisabled, setIsSubmitDisabled] = useState<boolean>(true);
    const [openAlert, setOpenAlert] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);

    //useEffect
    //validacion de token
    useEffect(() => {
        const token: boolean = validToken();
        if (token) {
            navigate("/app");
        }
    }, [navigate]);
    //validacion de campos
    useEffect(() => {
        if (
            usuario &&
            usuario.length > 3 &&
            contraseña &&
            contraseña.length > 5
        ) {
            setIsSubmitDisabled(false);
        } else {
            setIsSubmitDisabled(true);
        }
    }, [usuario, contraseña]);

    //extra renders
    //advertencias
    const alertUser = () => {
        if (usuario) {
            if (usuario?.length === 0) {
                return <p>El usuario es requerido</p>;
            } else if (usuario?.length < 4) {
                return <p>El usuario debe tener al menos 4 caracteres</p>;
            }
        }
        return null;
    };
    const alertPassword = () => {
        if (contraseña) {
            if (contraseña?.length === 0) {
                return <p>La contraseña es requerida</p>;
            } else if (contraseña?.length < 6) {
                return <p>La contraseña debe tener al menos 6 caracteres</p>;
            }
        }
        return null;
    };

    //functions
    //handlerButton
    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        if (usuario && contraseña) {
            apiService
                .login(usuario, contraseña)
                .then((response) => {
                    setToken(response.token);
                    navigate("/app");
                })
                .catch((error) => {
                    setError(error.message);
                    console.log(error);
                    setOpenAlert(true);
                });
        }
    };

    const handleClose = (
        _event?: React.SyntheticEvent | Event,
        reason?: string
    ) => {
        if (reason === "clickaway") {
            return;
        }

        setOpenAlert(false);
    };

    return (
        <div className="container" style={{ backgroundColor: primaryColor }}>
            <div className="login-container">
                <h2>LOGIN</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <TextField
                            id="usuario"
                            label="usuario"
                            variant="standard"
                            onChange={(e) => setUsuario(e.target.value)}
                        />
                    </div>
                    {alertUser()}

                    <div>
                        <TextField
                            id="contraseña"
                            label="contraseña"
                            variant="standard"
                            type="password"
                            onChange={(e) => setContraseña(e.target.value)}
                        />
                    </div>
                    {alertPassword()}
                    <Button
                        type="submit"
                        variant="contained"
                        disabled={isSubmitDisabled}
                    >
                        Iniciar sesión
                    </Button>
                </form>
                <hr />
                <Button onClick={() => navigate("/register")} variant="text">
                    Registrarse
                </Button>
            </div>

            <Snackbar
                open={openAlert}
                autoHideDuration={6000}
                onClose={handleClose}
            >
                <Alert
                    onClose={handleClose}
                    severity="error"
                    sx={{ width: "100%" }}
                >
                    {error ?? "Ocurrion un error durante el inicio de sesión"}
                </Alert>
            </Snackbar>
        </div>
    );
};

const Alert = React.forwardRef<HTMLDivElement, AlertProps>(function Alert(
    props,
    ref
) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export default Login;
