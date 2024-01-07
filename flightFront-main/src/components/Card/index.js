import React, { useEffect } from "react";
import { Box, Button, Image } from "@chakra-ui/react";
import moment from "moment";
import { Link } from "react-router-dom";

import { useBasket } from "../../contexts/BasketContext";


function Card({ item }) {
    console.log("ŞTEM=>4534", item);
    const { addToBasket, items } = useBasket();

    useEffect(() => {
        console.log("ADD TOBASKET", items)
    }, [items])

    const findBasketItem = items.find(
        (basket_item) => basket_item.id === item._id
    );

    return (
        <Box borderWidth="1px" borderRadius="lg" overflow="hidden" p="3">
            <Link to={`/flight/${item._id}`}>
                <Image src={item.image} alt="flight" loading="lazy" />
                <Box d="6">
                    <Box d="plex" alignItems="baseline">
                        {moment(item.createdAt).format('DD/MM/YYYY')}
                    </Box>
                    <Box mt="1" fontWeight="semibold" as="h4" lineHeight="tight">
                        {item.title}
                    </Box>
                    <Box>{item.price} TL</Box>
                </Box>
            </Link>
            <Button colorScheme={findBasketItem ? "pink" : "green"} variant="solid" onClick={() => addToBasket(item, findBasketItem)}>
                {findBasketItem ? "Remove from basket" : "Add to basket"}
            </Button>
        </Box>);


}
export default Card;
