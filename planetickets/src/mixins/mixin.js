const tempParser = {
    data(){
        
    },
    methods:{
        
        checkLoginStatus(){
            try{
                if(JSON.parse(localStorage.getItem('xmljwt'))==null){
                    this.$router.push(`/login`);
                }
            }catch(error){
                console.log("error: " + error);
            }
        },

        parseXmlJwt(){
            if(localStorage.getItem('xmljwt')){
                console.log("TOKEN JE:")
                console.log(localStorage.getItem('xmljwt'));
                if(localStorage.getItem('xmljwt').toString() != 'null'){
                    let parsedToken = this.parseJwt(localStorage.getItem('xmljwt'));
                    console.log('TOKEN KOJI SE PARSIRA: ' + JSON.stringify(parsedToken));
                    localStorage.setItem('parsedToken', JSON.stringify(parsedToken));
                    try{
                        let tempUsername = localStorage.getItem('parsedToken').split(",")[2].split(':')[1];
                        tempUsername = tempUsername.slice(1, tempUsername.length - 1).trim();
                        let tempRole = localStorage.getItem('parsedToken').split(",")[0].split(":")[1];
                        tempRole = tempRole.slice(1, tempRole.length - 1).trim();
                        let tempId = localStorage.getItem('parsedToken').split(",")[1].split(':')[1];
                        console.log("ID PRE TRIMOVANJA: " + tempId)
                        tempId = tempId.trim();

                        let userObj = new Object();
                        userObj.id = tempId;
                        userObj.role = tempRole;
                        userObj.email = tempUsername;
                        console.log("AAAAWDAWDAW")
                        console.log(JSON.stringify(userObj));
                        return userObj;
                        
                    }catch(error){
                        // console.log("Greska pri parsiranju jwt-a: " + error.message);
                        // localStorage.setItem('xmljwt', null);
                        // localStorage.setItem('parsToken', null);
                    }
                }
            }
            
        },

        parseJwt(token){
            console.log("MIXIN PARSIRANJE");
            if(token != null){
                var base64Url = token.split('.')[1];
                var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
                var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
                    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
                }).join(''));
        
                return JSON.parse(jsonPayload);
            }
        }
    }
}
export default tempParser;