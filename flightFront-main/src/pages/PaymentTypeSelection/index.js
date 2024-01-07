import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Box, Button } from '@chakra-ui/react';

function PaymentTypeSelection() {
    const navigate = useNavigate();

    const navigateToPaymentPage = (paymentType) => {
        navigate(`/payment/${paymentType}`);
    };

    return (
        <Box textAlign="center">
            <Button onClick={() => navigateToPaymentPage('credit-card')} m="2">
                Kredi Kartı ile Öde
            </Button>
            <Button onClick={() => navigateToPaymentPage('bank-card')} m="2">
                Banka Kartı ile Öde
            </Button>
        </Box>
    );
}

export default PaymentTypeSelection;
