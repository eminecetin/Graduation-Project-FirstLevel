import React from 'react'; 
import  './styles.css';

import {Box} from '@chakra-ui/react';
import { Link, Route, Routes, useRouteMatch } from 'react-router-dom';

import Home from "./Home"
import Orders from "./Orders"
import Flights from "./Flights"
import FlightDetail from './FlightDetail';
import NewFlight from './Flights/new';

function Admin(){
    const {path, url} = useRouteMatch();


    return (
        <div>
            <nav>
                <ul className='admin-menu'>
                    <li>
                        <Link to={url}>Home</Link>
                    </li>
                    <li>
                        <Link to={`${url}/orders`}>Orders</Link>
                    </li>
                    <li>
                        <Link to={`${url}/flights`}>Flights</Link>
                    </li>
                </ul>
            </nav>

            <Box mt="10">
                <Routes>
                    <Route exact path={path} element={Home}/>
                    <Route path={`${path}/orders`} element={<Orders/>}/>
                    <Route path={`${path}/flights`} element={<Flights/>}/>
                    <Route path={`${path}/flights/new`} element={<NewFlight/>}/>
                    <Route path={`${path}/flights/:flight_id`} element={<FlightDetail/>}/>
                </Routes>
            </Box>

        </div>
    )
}

export default Admin