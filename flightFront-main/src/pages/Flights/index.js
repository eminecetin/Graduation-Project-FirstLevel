import React, { useState, useEffect } from "react";
import { Grid, Box, Flex, Button, Select, Input, Image, Heading } from '@chakra-ui/react';
import { fetchFlightList } from "../../api";
import Card from "../../components/Card";
import SeatMap from "../seatMap";

function Flights() {
    const [flightData, setFlightData] = useState(null);
    const [filters, setFilters] = useState({ 
        departureCountry: '', departureCity: '', departureAirport: '', 
        arrivalCountry: '', arrivalCity: '', arrivalAirport: '', 
        date: '' 
    });

    useEffect(() => {
        fetchFilteredFlights();
    }, []);

    const fetchFilteredFlights = async () => {
        try {
            const data = await fetchFlightList(filters);
            setFlightData(data);
        } catch (error) {
            console.error("Error fetching data:", error);
        }
    };

    const handleFilterChange = (e) => {
        setFilters({ ...filters, [e.target.name]: e.target.value });
    };

    const handleSearch = () => {
        fetchFilteredFlights();
    };

    return (
        <Flex direction="column" align="center" justify="start" minHeight="100vh">
            <Heading as="h1" size="xl" textAlign="center" m="4">
                SEYAHATLERİNİZ BİZİMLE GÜVENDE
            </Heading>

            <Box p="4" borderWidth="1px" borderRadius="lg" overflow="hidden" mb="20px">
                <Flex mb="20px" justifyContent="center" gridGap="20px">
                    <Select placeholder="Kalkış Ülkesi" onChange={handleFilterChange} name="departureCountry">
                        {/* Kalkış ülkesi seçenekleri */}
                    </Select>
                    <Select placeholder="Kalkış Şehri" onChange={handleFilterChange} name="departureCity">
                        {/* Kalkış şehri seçenekleri */}
                    </Select>
                    <Select placeholder="Kalkış Havaalanı" onChange={handleFilterChange} name="departureAirport">
                        {/* Kalkış havaalanı seçenekleri */}
                    </Select>
                    <Select placeholder="Varış Ülkesi" onChange={handleFilterChange} name="arrivalCountry">
                        {/* Varış ülkesi seçenekleri */}
                    </Select>
                    <Select placeholder="Varış Şehri" onChange={handleFilterChange} name="arrivalCity">
                        {/* Varış şehri seçenekleri */}
                    </Select>
                    <Select placeholder="Varış Havaalanı" onChange={handleFilterChange} name="arrivalAirport">
                        {/* Varış havaalanı seçenekleri */}
                    </Select>
                    <Input type="date" onChange={handleFilterChange} name="date" />
                    <Button colorScheme="blue" onClick={handleSearch}>Ara</Button>
                </Flex>
            </Box>

            <Image src="src/image/images.jpeg" alt="Havaalanı ve Uçuşlar" />

            <Grid templateColumns='repeat(4, 1fr)' gap={6} mt="20px">
                {flightData ?
                    flightData.map((group, i) => (
                        <Box w="100%">
                            <Card item={group} />
                        </Box>
                    )) : <div>Sayfa Yükleniyor!..</div>
                }
            </Grid>
            <SeatMap planeType="AIRBUS_A320" />

        </Flex>
    );
}

export default Flights;

