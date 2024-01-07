import React from 'react';
import { Box, Grid, GridItem, Text } from '@chakra-ui/react';

// Bu fonksiyon, belirli bir uçak tipi için koltukları oluşturur.
const createSeats = (businessSeats, economySeats, premiumEconomySeats) => {
    const seats = [];
    
    // İş sınıfı koltukları ekleme
    for (let i = 0; i < businessSeats; i++) {
        seats.push(<GridItem w="100%" h="10" bg="blue.300"><Text textAlign="center">Business</Text></GridItem>);
    }

    // Premium ekonomi sınıfı koltukları ekleme (varsa)
    if (premiumEconomySeats) {
        for (let i = 0; i < premiumEconomySeats; i++) {
            seats.push(<GridItem w="100%" h="10" bg="purple.300"><Text textAlign="center">Premium Economy</Text></GridItem>);
        }
    }

    // Ekonomi sınıfı koltukları ekleme
    for (let i = 0; i < economySeats; i++) {
        seats.push(<GridItem w="100%" h="10" bg="green.300"><Text textAlign="center">Economy</Text></GridItem>);
    }

    return seats;
};

const SeatMap = ({ planeType }) => {
    let seatLayout;

    switch (planeType) {
        case "AIRBUS_A320":
            seatLayout = createSeats(30, 130, 0); // AIRBUS_A320 için koltuk ayarları
            break;
        case "AIRBUS_A380":
            seatLayout = createSeats(50, 140, 0); // AIRBUS_A380 için koltuk ayarları
            break;
        case "BOEING_737":
            seatLayout = createSeats(20, 130, 0); // BOEING_737 için koltuk ayarları
            break;
        case "BOEING_747":
            seatLayout = createSeats(60, 180, 60); // BOEING_747 için koltuk ayarları
            break;
        default:
            seatLayout = <Text>Uçak tipi tanımlanmamış</Text>;
            break;
    }

    return (
        <Box w="100%" p={4}>
            <Grid templateColumns="repeat(auto-fit, minmax(120px, 1fr))" gap={2}>
                {seatLayout}
            </Grid>
        </Box>
        
    );
};

export default SeatMap;
