import React, { useState } from 'react';
import { Box, FormControl, FormLabel, Input, Button } from '@chakra-ui/react';

function BankCardPayment() {
    const [paymentInfo, setPaymentInfo] = useState({
        cardNumber: '',
        cardName: '',
        expiryDate: '',
        cvv: ''
    });

    const handleChange = (e) => {
        setPaymentInfo({ ...paymentInfo, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Burada ödeme işlemi için API çağrısı yapılabilir
        console.log('Ödeme Bilgileri:', paymentInfo);
        alert('Banka Kartı ile Ödeme işlemi gerçekleştirildi!');
    };

    return (
        <Box maxWidth="400px" margin="auto" p="5">
            <form onSubmit={handleSubmit}>
                <FormControl isRequired>
                    <FormLabel>Kart Numarası</FormLabel>
                    <Input 
                        type="text" 
                        name="cardNumber"
                        onChange={handleChange}
                        value={paymentInfo.cardNumber}
                    />
                </FormControl>
                <FormControl isRequired mt="4">
                    <FormLabel>Kart Üzerindeki İsim</FormLabel>
                    <Input 
                        type="text"
                        name="cardName"
                        onChange={handleChange}
                        value={paymentInfo.cardName}
                    />
                </FormControl>
                <FormControl isRequired mt="4">
                    <FormLabel>Son Kullanma Tarihi</FormLabel>
                    <Input 
                        type="text"
                        name="expiryDate"
                        onChange={handleChange}
                        value={paymentInfo.expiryDate}
                        placeholder="AA/YY"
                    />
                </FormControl>
                <FormControl isRequired mt="4">
                    <FormLabel>CVV / CVC</FormLabel>
                    <Input 
                        type="text" 
                        name="cvv"
                        onChange={handleChange}
                        value={paymentInfo.cvv}
                    />
                </FormControl>
                <Button mt="4" width="full" type="submit" colorScheme="blue">
                    Öde
                </Button>
            </form>
        </Box>
    );
}

export default BankCardPayment;
