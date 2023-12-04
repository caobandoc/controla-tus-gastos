import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";

//router
import { createBrowserRouter, RouterProvider } from "react-router-dom";

//pages
import Home from "./feature/home/Home.tsx";
import NotFound from "./feature/notfound/NotFound.tsx";
import Login from "./feature/login/Login.tsx";
import Admin from "./feature/admin/Admin.tsx";

//mui
import CssBaseline from "@mui/material/CssBaseline";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Home />,
    },
    {
        path: "/login",
        element: <Login />,
    },
    {
        path: "/app",
        element: <Admin />,
    },
    {
        path: "*",
        element: <NotFound />,
    },
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
    <React.StrictMode>
        <CssBaseline />
        <RouterProvider router={router} />
    </React.StrictMode>
);
