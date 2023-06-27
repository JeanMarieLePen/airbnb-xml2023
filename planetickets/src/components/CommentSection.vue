<template>
    <div style="margin-left:20px; margin-bottom:25px;">
        <div>
            <h2>Komentari</h2>
        </div>
        <div id="new-comment-form">
            <table>
                <thead>
                    <th colspan="2">
                        Dodajte Vas komentar
                    </th>
                </thead>
                <tr>
                    <td colspan="2">
                        <textarea style="width:100%;" placeholder="Upisite tekst komentara(max 500 karaktera)..." v-model="comment.tekst">
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <star-rating @update:rating="setRating" style="width:300px; font-size:30px;"></star-rating>
                    </td>
                    <td>
                        <button class="btn btn-warning">Kreiraj</button>
                    </td>
                </tr>
            </table>
            
            
        </div>
        <div id="comment-section">
            <div>
                <div  v-for="(tempComment, index) in comments" v-bind:key="index" class="comments-box">
                <div>
                    <p class="author">
                    {{tempComment.autor}} 
                    <star-rating :star-size="20" read-only style="margin-left:10px;display:inline-block"  v-model:rating="tempComment.ocena"></star-rating>
                    </p>
                </div>
            
                <p v-html="tempComment.tekst" class="content-comment"></p>
                <!-- <p v-show="isAdmin" @click="removeComment(index)" class="delete">Delete</p> -->
                <p style="font-weight:200; font-style:italic">Kreiran: {{tempComment.datumIVreme}}</p>
                <p style="font-weight:500; font-style:italic">Rejting: {{tempComment.rejting}}</p>
                <div id="like-section">
                    <button @click="like()">
                        <div>
                            <img src="../assets/like.png" style="max-width:100%; max-height:100%;" />
                        </div>
                    </button>
                    <button @click="dislike()">
                        <div>
                            <img src="../assets/dislike.png" style="max-width:100%; max-height:100%;" />
                        </div>
                    </button>
                </div>
                </div>
                <div style="margin-left: 200px;margin-top:100px" v-show="comments.length == 0 ">
                    <h1>Trenutno nema komentara</h1>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import dataService from '../services/dataService'
import parserMixin from '@/mixins/mixin'
import StarRating from 'vue-star-rating'
export default {
    components:{
        StarRating,
    },
    data(){
        return{
            // comments:[],
            comments:[
                {
                    id:1,
                    tekst:'Odlicno, bravo.',
                    ocena: 4,
                    datumIVreme: '2022-04-15',
                    autor:"Branimir",
                    rejting:66
                },
                {
                    id:2,
                    tekst:'Vrlo lose.',
                    ocena: 1,
                    datumIVreme: '2012-01-5',
                    autor:"Tanja",
                    rejting:-24
                },
                {
                    id:3,
                    tekst:'Preporuke.',
                    ocena: 4,
                    datumIVreme: '2021-12-10',
                    autor:"Milica",
                    rejting:56
                }
            ],
            userObj:{

            },
            idEntity:-1,
            comment:{
                tekst:'',
                ocena:'',
            }
        }
    },
    methods:{
        async getComments(){
            dataService.getEntityComments(this.idEntity).then(response => {
                console.log("Dobavljeni komentari");
                this.comments = response.data;
            });
        },
        setRating(rating){
            console.log("DATA OCENA: " + rating);
            this.comment.ocena = rating;
        },
        dislike(){
            dataService.dislikeComment().then(response => {

            });
        },
        like(){
             dataService.likeComment().then(response => {

            });
        }
    },
    created(){
        // parserMixin.methods.checkLoginStatus();
        // this.userObj = parserMixin.methods.parseXmlJwt();
        // console.log("ID KORISNIKA: " + this.userObj.id)
        // this.idEntiteta = this.$route.params.id;

    },
    mounted(){

    }
}
</script>


<style scoped>

#new-comment-form{
    width:1000px;
    height: 30vh;
}
#new-comment-form>table{
    width:60vh;
    height:250px;
}
#new-comment-form textarea{
    height:150px;
    resize: none;
}
#new-comment-form button{
    /* height:30px; */
    font-size: 25px;
    resize: none;
}
#comment-section{
    margin-bottom:100px;
    width:60%;
}
.comments-box{
    width: 90%;
    padding: 20px 0;
    border-bottom: 1px solid #000;
}
.author{
    margin: 10px 0;
    font-weight: bold;
}
#like-section button{
    height:50px;
    width: 50px;
    text-align: center;
    background: white;
}
</style>