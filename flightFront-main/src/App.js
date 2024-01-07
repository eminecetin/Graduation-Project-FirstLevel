import React from 'react';
import Navbar from './components/Navbar';
import Flights from "./pages/Flights";
import FlightDetail from "./pages/FlightDetail"
import Signin from "./pages/Auth/Signin";
import AdminSignin from "./pages/Auth/AdminSignin";
import Signup from "./pages/Auth/Signup";
import Profile from "./pages/Profile";
import Basket from "./pages/Basket";
import ProtectedRoute from './pages/ProtectedRoute';
import Error404 from './pages/Error404';
import PaymentTypeSelection from './pages/PaymentTypeSelection';
import CreditCardPayment from './pages/CreditCardPayment';
import BankCardPayment from './pages/BankCardPayment';
import SeatMap from './pages/seatMap';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';



function App() {
  return (
    // <BrowserRouter>
    //   <div>
    //     <Navbar/>
    //     <div id="content">
    //       <Route>
    //         <Route path="/" exact element={<Flights />} />
    //         <Route path="/flight/:flight_id" element={<FlightDetail />} />
    //         <Route path="/signin" element={<Signin />} />
    //         <Route path="/signup" element={<Signup />} />
    //         <Route path="/basket" element={<Basket />} />
    //         <ProtectedRoute path="/profile" component={Profile}/>
    //         <Route path="*" element={<Error404 />} />
    //       </Route>
    //     </div>
    //   </div>
    // </BrowserRouter>
    <Router>
    <Navbar />
    <Routes>
      <Route path="/" element={<Flights />} />
      <Route path="/flight/:flight_id" element={<FlightDetail />} />
      <Route path="/signin" element={<Signin />} />
      <Route path="/adminsignin" element={<AdminSignin />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/cardtype" element={<PaymentTypeSelection />} />
      <Route path="/payment/credit-card" element={<CreditCardPayment />} />
      <Route path="/payment/bank-card" element={<BankCardPayment />} />
      <Route path="/seatmap" element={<SeatMap />} />
      <Route path="/basket" element={<Basket />} />
      <Route 
        path="/profile" 
        element={
          <ProtectedRoute>
            <Profile />
          </ProtectedRoute>
        } 
      />
      <Route path="*" element={<Error404 />} />
    </Routes>
  </Router>
);
}

export default App;

