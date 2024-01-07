import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

function ProtectedRoute({ component: Component,admin, ...rest }) {
    const { loggedIn,user } = useAuth();

    if (admin && user.role !=="admin"){
        return <Navigate to = {{pathname: "/"}}/>
    }

    // Eğer kullanıcı giriş yapmışsa, istenen bileşeni göster
    if (loggedIn) {
        return <Component {...rest} />;
    }

    // Kullanıcı giriş yapmamışsa, ana sayfaya veya giriş sayfasına yönlendir
    return <Navigate to="/" />;
}

export default ProtectedRoute;
