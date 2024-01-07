import axios from 'axios';

axios.interceptors.request.use(
    function (config){
        const {origin}=new URL(config.url);

        const allowedOrigins=[process.env.REACT_APP_BASE_ENDPOINT];
        const token =localStorage.getItem('access-token');

        if(allowedOrigins.includes(origin)){
            config.headers.authorization = token
        }
        return config;
    }
);

export const fetchFlightList = async () => {

    try {
        const response = await axios.get("http://localhost:8085/flights");
        const data = response.data;

        // console.log(data); // You can log the data if you want

        return data;
    } catch (error) {
        console.error("Error fetching flight list:", error);
        // Handle the error or throw it if needed
        throw error;
    }
};
/*export const fetchFlight = async (id) => {
    const { data } = await axios.get(`${process.env.REACT_APP_BASE_ENDPOINT}/product/${id}`);
    return data;
};*/

export const fetchFlight = async (id) => {  //denemek için yeni yazdımm
    try {
        const response = await axios.get(`http://localhost:8085/flights/${id}`);
        const data = response.data;

        // console.log(data); // Veriyi loglamak isterseniz kullanabilirsiniz

        return data;
    } catch (error) {
        console.error("Error fetching flight:", error);
        // Hata işleme veya gerektiğinde hata fırlatma
        throw error;
    }
};

export const postFlight  = async(input)=>{
    const {data} =await axios.post(
        `${process.env.REACT_APP_BASE_ENDPOINT}/flight/`,
        input
    );
    return data;
};

export const fetchRegister =async(input)=>{
    const {data} =await axios.post(
        `${process.env.REACT_APP_BASE_ENDPOINT}/auth/register`,
        input
    );
    return data;
};

export const fetchLogin=async (input) => {
    const {data} =await axios.post(
        `${process.env.REACT_APP_BASE_ENDPOINT}/auth/login`,
        input
    );
    return data;
};

export const fetchAdminLogin=async (input) => {
    const {data} =await axios.post(
        `${process.env.REACT_APP_BASE_ENDPOINT}/auth/login`,
        input
    );
    return data;
};


export const fetchMe=async()=>{
    const {data}=await axios.get(
        `${process.env.REACT_APP_BASE_ENDPOINT}/auth/me`
    );
    return data;
};

export const fetchLogout= async ()=>{
    const {data} = await axios.post(`${process.env.REACT_APP_BASE_ENDPOINT}/auth/logout`,{
        refresh_token: localStorage.getItem('refresh-token')
    });
    return data;
};

export const postOrder=async(input)=>{
    const {data} = await axios.post(`${process.env.REACT_APP_BASE_ENDPOINT}/order`,
    input
    );
    return data;
};

export const fetchOrders=async () =>{
    const {data} =await axios.get(`${process.env.REACT_APP_BASE_ENDPOINT}/order`
    );
    return data;
};

export const deleteFlight=async(flight_id)=>{
    const {data} =await axios.delete(`${process.env.REACT_APP_BASE_ENDPOINT}/flight/${flight_id}`
    );

    return data;
};

export const updateFlight = async (input, flight_id) => {
    const {data} =await axios.put(
        `${process.env.REACT_APP_BASE_ENDPOINT}/flight/${flight_id}`,
        input
    );
    return data;
};

