import axios from "axios"


const API_URL = "http://localhost:7777";

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

axios.interceptors.request.use(request => {
    console.log('Starting Request', JSON.stringify(request, null, 2))
    return request
  })

  axios.interceptors.response.use(response => {
    console.log('Response:', JSON.stringify(response, null, 2))
    return response
  })

class DataService{
    login(loginForm){
        return axios.post(`${API_URL}/login/authenticate`, loginForm);
    }
    register(form){
        return axios.post(`${API_URL}/login/register`, form);
    }
    getUser(id){
        return axios.get(`${API_URL}/korisnik/${id}`);
    }
    putUser(id, profile){
        return axios.put(`${API_URL}/korisnik/${id}`, profile);
    }
    updateUserProfile(profile){
        return axios.put(`${API_URL}/korisnik/update`, profile);
    }
    getSmestaj(id){
        return axios.get(`${API_URL}/smestaj/getByIdSmestaja/${id}`);
    }
    createNewSmestaj(smestaj){
        return axios.post(`${API_URL}/smestaj/create`, smestaj);
    }
    editSmestaj(smestajId, vlasnikId, smestaj){
        return axios.put(`${API_URL}/smestaj/edit/${smestajId}/${vlasnikId}`, smestaj)
    }
    getAllPogodnosti(){
        return axios.get(`${API_URL}/smestaj/getAllPogodnosti`);
    }
    makeReservation(reservation, userId, smestajId){
        return axios.post(`${API_URL}/korisnik/makeReservation/${userId}/${smestajId}`, reservation);
    }

    getRezervacijeByHost(id){
        return axios.get(`${API_URL}/host/getAllReservationsByUser/${id}`);
    }
    getRezervacijeByKorisnik(id){
        return axios.get(`${API_URL}/korisnik/getAllReservationsByUser/${id}`);
    }
    getOwnerById(id){
        return axios.get(`${API_URL}/host/getOwnerById/${id}`);
    }
    cancelRezervaciju(userId, reservationId){
        return axios.put(`${API_URL}/korisnik/cancelReservation/${userId}/${reservationId}`);
    }
    approveReservation(reservationId, ownerId){
        return axios.put(`${API_URL}/host/approveReservation/${ownerId}/${reservationId}`);
    }
    rejectReservation(reservationId, ownerId){
        return axios.put(`${API_URL}/host/rejectReservation/${ownerId}/${reservationId}`);
    }
    canGiveRating(userId, smestajId){
        return axios.get(`${API_URL}/korisnik/canGiveRating/${userId}/${smestajId}`);
    }
    giveRatingToSmestaj(userId, smestajId, ocenaObj){
        return axios.post(`${API_URL}/korisnik/giveRatingToSmestaj/${userId}/${smestajId}`, ocenaObj);
    }

}


export default new DataService();