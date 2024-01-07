import React from "react";
import { useParams } from 'react-router-dom';
import { useQuery } from "react-query";
import { fetchFlight } from "../../api";
import { Button, Text, Box } from "@chakra-ui/react";
import ReactImageGallery from "react-image-gallery";
import moment from 'moment';
import { useBasket } from "../../contexts/BasketContext";
import SeatMap from "../../pages/seatMap";

function FlightDetail() {
    const { flight_id } = useParams();
    const { addToBasket, items } = useBasket();

    const { isLoading, isError, data } = useQuery(["flight", flight_id], () => fetchFlight(flight_id));

    if (isLoading) {
        return <Box textAlign="center">Uçuş Detayları Yükleniyor...</Box>
    }

    if (isError) {
        return <Box textAlign="center">Uçuş detayları yüklenirken hata oluştu.</Box>
    }

    const findBasketItem = items.find((item) => item.id === flight_id);
    const images = data.photos.map((url) => ({ original: url }));

    return (
        <div>
            <Button colorScheme={findBasketItem ? "pink" : "green"} onClick={() => addToBasket(data, findBasketItem)}>
                {findBasketItem ? 'Sepetten Çıkar' : 'Sepete Ekle'}
            </Button>
            <Text as="h2" fontSize="2xl" mt="4">
                {data.title}
            </Text>
            <Text fontSize="lg" my="2">{moment(data.createdAt).format('DD/MM/YYYY')}</Text>
            <Text fontSize="md" mb="4">{data.description}</Text>
            <Box margin="10">
                <ReactImageGallery items={images} showThumbnails={false} />
            </Box>
            {data.planeType && <SeatMap planeType={data.planeType} />}
        </div>
    );  
}

export default FlightDetail;
