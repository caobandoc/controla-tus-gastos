import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { validToken } from "../../core/services/tokenService";

const Admin: React.FC = () => {
    //navigate
    const navigate = useNavigate();

    useEffect(() => {
        const token: boolean = validToken();
        if (!token) {
            navigate("/login");
        }
    }, [navigate]);

    return (
        <div>
            <h1>Admin</h1>
            {/* Aquí puedes agregar el contenido de tu componente */}
        </div>
    );
};

export default Admin;
