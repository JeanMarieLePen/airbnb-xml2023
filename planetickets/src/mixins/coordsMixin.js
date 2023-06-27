import {YandexMap, YandexMarker} from "vue-yandex-maps"
import { loadYmap } from "vue-yandex-maps";

const coordsFinder = {

    data(){
        return{
            settings: {
                apiKey: "5e04929d-e957-4fdd-b85c-08e60b3cb3f0",
                lang: "en_US",
                coordorder: "latlong",
                enterprise: false,
                version: "2.1",
            },
        }
    },
    components:{
        YandexMap, YandexMarker,
    },
    async mounted(){
        console.log("MONTIRANJE")
        await loadYmap({ ...this.settings, format: "json", debug: true});
        // await this.getEntity();
        // await this.getCoordsFromAdresa();
    },
    methods:{
        // async getCoordsFromAdresa(adresa){
        //     ymaps.geocode(adresa).then((result) => {
        //         let locationCoords = {
        //             coords: result.geoObjects.get(0).geometry.getCoordinates(),
        //         };
        //         let location = new Object();
        //         location.lat = locationCoords.coords[0];
        //         location.lng = locationCoords.coords[1];

        //     });
        // },
        async getCoordsFromAdresa(adresa){
            return ymaps.geocode(adresa).then((result) => {
                let locationCoords = {
                    coords: result.geoObjects.get(0).geometry.getCoordinates(),
                };
                this.lokacija.lat = locationCoords.coords[0];
                this.lokacija.lng = locationCoords.coords[1];
                this.form.adresa.lat = this.lokacija.lat;
                this.form.adresa.lng = this.lokacija.lng;
                console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
            });
        }
    }
}
export default coordsFinder;