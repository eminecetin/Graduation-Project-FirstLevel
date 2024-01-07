import styles from "./styles.module.css";
import { Button } from '@chakra-ui/react'
import { Link } from 'react-router-dom';
import {useAuth} from "../../contexts/AuthContext";
import {useBasket} from "../../contexts/BasketContext";


function Navbar(){
    const {loggedIn, user} = useAuth();
    const {items} = useBasket();

    return(
        <nav className={styles.nav}>
           <div className= {styles.left} >
            <div classsName={styles.logo}>
            <Link to="/"> Ticket Reservation </Link>
           </div>
           <ul className={styles.menu}>
            <li>
              <Link to="/">Flights</Link>
            </li>
           </ul>
           </div>
           <div className={styles.right}>
            {!loggedIn &&(
                <>
            <Link to="/signup">
            <Button colorScheme='pink'>Register</Button>
            </Link> 
            <Link to="signin">
            <Button colorScheme='pink'>Login</Button>
            </Link>
            <Link to="adminsignin">
            <Button colorScheme='yellow'>Admin Signin</Button>
            </Link> 
            <Link to="seatmap">
            <Button colorScheme="green">SeatMap</Button>
            </Link>
            <Link to="basket">
            <Button colorScheme='green'>Basket</Button>
            </Link>    
             <Link to="cardtype">
            <Button colorScheme='green'>Payment</Button>
            </Link>    
             <Link to="payment/credit-card">
            <Button colorScheme='green'>Credit Card</Button>
            </Link>  
            <Link to="payment/bank-card">
            <Button colorScheme='green'>Bank Kard</Button>
            </Link>  
                </>
            )}
            {
                loggedIn &&(
                    <>
                    {
                        items.length> 0 && (
                            <Link to="/basket">
                                <Button colorShema="pink" variant="outline">
                                    Basket({items.length})
                                </Button>
                            </Link>
                        )
                    }
                    {
                        user?.role==='admin' && (
                            <Link to="/admin">
                                <Button colorScheme="pink" variant="ghost">
                                    Admin
                                </Button>
                            </Link>
                        )
                    }
                    <Link to="profile">
                        <Button colorScheme="pink">Profile</Button>
                    </Link>
                    </>
                )}
           </div>
        </nav>
    );
}

export default Navbar;