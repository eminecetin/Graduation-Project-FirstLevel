import React from 'react';
import { Text, Button } from "@chakra-ui/react";
import { useAuth } from "../../contexts/AuthContext";
import { useNavigate } from 'react-router-dom'; // Import useNavigate

function Profile() {
    const { user, logout } = useAuth();
    const navigate = useNavigate(); // Initialize navigate

    const handleLogout = async () => {
        logout(() => {
            navigate("/"); // Use navigate instead of history.pushState
        });
    };

    return (
        <div>
            <Text fontSize="22">Profile</Text>
            <code>{JSON.stringify(user)}</code> {/* Correct the typo in stringify */}

            <br />
            <br />
            <br />
            <Button colorScheme='pink' variant="solid" onClick={handleLogout}>
                Logout
            </Button>
        </div>
    );
}

export default Profile;
