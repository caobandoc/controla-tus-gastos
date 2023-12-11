import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./register.css";

//mui
import { Button, useTheme } from "@mui/material";
import TextField from "@mui/material/TextField";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert, { AlertProps } from "@mui/material/Alert";

//services
import userRegister from "../../core/services/userService";

const Register: React.FC = () => {
    //variables
    const theme = useTheme();
    const primaryColor = theme.palette.primary.main;

    //navigate
    const navigate = useNavigate();

    //hooks
    const [email, setEmail] = useState<string | null>(null);
    const [usuario, setUsuario] = useState<string | null>(null);
    const [contraseña, setContraseña] = useState<string | null>(null);
    const [isSubmitDisabled, setIsSubmitDisabled] = useState<boolean>(true);
    const [openAlert, setOpenAlert] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);

    //useEffect
    //validacion de campos
    useEffect(() => {
        if (
            usuario &&
            usuario.length > 3 &&
            contraseña &&
            contraseña.length > 5 &&
            email &&
            validateEmail(email)
        ) {
            setIsSubmitDisabled(false);
        } else {
            setIsSubmitDisabled(true);
        }
    }, [usuario, contraseña, email]);

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
    const alertEmail = () => {
        if (email) {
            if (email?.length === 0) {
                return <p>El email es requerido</p>;
            } else if (email && !validateEmail(email)) {
                return <p>El email no es valido</p>;
            }
        }
        return null;
    };

    //functions
    //handlerButton
    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        if (usuario && contraseña && email) {
            userRegister
            .register(usuario, contraseña, email)
            .then(() => navigate("/login"))
            .catch((error) => {
                setError(error.message);
                setOpenAlert(true);
            });
        }
    };
    const validateEmail = (email: string) => {
        const re = /\S+@\S+\.\S+/;
        return re.test(email);
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
                            id="email"
                            label="email"
                            variant="standard"
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    {alertEmail()}
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
                        Registrarse
                    </Button>
                </form>
                <hr />
                <Button onClick={() => navigate("/login")} variant="text">
                    Ingresar
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

export default Register;
