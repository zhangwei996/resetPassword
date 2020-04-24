<template>
    <div>
        <!-- {{quesAnswer}} -->
        <div v-if="isMobile" class="my-date-box" >
            <span  :class="{'isCheck':!checkDate,'my-input':true}" >{{selText}}</span>
            <input v-if="dateType==1"  type="date" v-model="checkDate" class="my-input-date" >  
            <input v-if="dateType==2"  type="time" v-model="checkDate" class="my-input-date"  >  
            <input v-if="dateType==3"  type="datetime-local" v-model="checkDate" class="my-input-date">  
        </div>
       <div v-else>
            <DatePicker v-if="dateType==1" type="date" v-model="checkDate" :placeholder="placeholder"  transfer style="width:220px"></DatePicker>
            <TimePicker v-if="dateType==2" type="time" v-model="checkDate" :placeholder="placeholder"  transfer style="width:220px"></TimePicker >
            <DatePicker v-if="dateType==3" type="datetime" v-model="checkDate" :placeholder="placeholder"  transfer style="width:220px"></DatePicker>
       </div>

    </div>
</template>

<script>
export default {
     props:{
        quesAnswer:String,
        placeholder:String,
        dateType:Number,
    },
    model:{
        prop:"quesAnswer",
        event:'awser',
    },
     data(){
        return {
            isMobile:false,
            checkDate:this.quesAnswer,
        }
    },
    computed:{
        selText(){
            if(this.isMobile && this.checkDate==null){
                return this.placeholder;  
            }
            else{
                return this.checkDate;
            }
        },
    },
     watch:{
        checkDate(currVal){
            let text='';
            if(currVal){
                 if(this.dateType==1) text=$.dateFormat2( new Date(currVal),'yyyy-MM-dd');
                else if(this.dateType==3) text=$.dateFormat( new Date(currVal));
                else text=currVal;
                this.$emit('awser',text);
            }
        }
    },
    created(){
        // this.date=new Date();
        var deviceWidth = document.documentElement.clientWidth || window.innerWidth;
        this.isMobile=deviceWidth<960;
    },
}
</script>

<style scoped>
.isCheck{
    color: #666;
}
.my-date-box{position: relative;}
.my-date-box span{
    height: 34px;
}
.my-date-box input{
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    width: 100%;
    opacity: 0;
}


</style>