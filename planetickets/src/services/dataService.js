import axios from "axios"

const API_URL = "http://localhost:8084";

(function() {
    let xmljwt = localStorage.getItem('xmljwt');
    axios.defaults.headers.common['Access-Control-Allow-Headers'] = 'Authorization';
    if(xmljwt){
        console.log("POZVANA SELF-EXECUTING METODA: TOKEN SMESTEN U AUTHORIZATION HEADER");
        axios.defaults.headers.common['Authorization'] ='Bearer ' + xmljwt.slice(1, xmljwt.length-1);
        console.log("ZAGLAVLJE: " + axios.defaults.headers.common['Authorization']);
    }else{
        console.log("AXIOS ERROR");
        axios.defaults.headers.common['Authorization'] = null;
    }
})();

// axios.interceptors.request.use(request => {
//     console.log('Starting Request', JSON.stringify(request, null, 2))
//     return request
//   })

//   axios.interceptors.response.use(response => {
//     console.log('Response:', JSON.stringify(response, null, 2))
//     return response
//   })


  class DataService{
    login(loginForm){
        return axios.post(`${API_URL}/login2/authenticate`, loginForm);
    }
    register(form){
        return axios.post(`${API_URL}/login2/register`, form);
    }
    getUser(id){
        return axios.get(`${API_URL}/regkor/${id}`);
    }
    updateUserProfile(profile){
        return axios.put(`${API_URL}/regkor/update`, profile);
    }
    createNewFlight(flight){
        return axios.post(`${API_URL}/let/addNew`, flight);
    }
    getAllFlights(){
        return axios.get(`${API_URL}/let/getAll`);
    }
    getUserFlights(id){
        return axios.get(`${API_URL}/regkor/getPorudzbineByUser/${id}`)
    }
    getFlight(id){
        return axios.get(`${API_URL}/let/getById/${id}`)
    }
    rezervisi(rez){
        return axios.post(`${API_URL}/regkor/reservation`, rez)
    }
    ukloniRez(user,por){
        return axios.put(`${API_URL}/regkor/cancelReservation/${user}/${por}`)
    }
    searchQuery(pretraga){
        return axios.post(`${API_URL}/let/pretraga`,pretraga)
    }
    ukloniLet(id){
        return axios.put(`${API_URL}/let/deleteLet/${id}`)
    }
    deleteUserProfile(id){
        return axios.delete(`${API_URL}/regkor/deleteAccount/${id}`);
    }
    generateToken(dto, id){
        return axios.post(`${API_URL}/regkor/generateToken/${id}`, dto);
    }
    checkToken(id){
        return axios.get(`${API_URL}/regkor/checkToken/${id}`);
    }
}


export default new DataService();