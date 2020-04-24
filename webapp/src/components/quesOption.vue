<template>
    <!-- 题目选项组件 -->
    <div>
        <div v-if="quesType==1" class="option-item" v-for="(item,index) in quesConfig.options">
            <div class="my-radio">
                <input type="radio" :id="getByIds(index)"  v-model="quesAnswerTemp[0]" :name="timestamp" :value="index" />
                <label :for="getByIds(index)">{{item}}</label>
            </div>
        </div>
        <div v-if="quesType==2" class="option-item" v-for="(item,index) in quesConfig.options">
            <div class="my-checkbox">
                <input type="checkbox" :id="getByIds(index)"  v-model="quesAnswerTemp" :name="timestamp" :value="index" />
                <label :for="getByIds(index)">{{item}}</label>
            </div>
        </div>
        <div v-if="quesType==3" class="option-item">
            <div class="mt15 mb15">
                <select class="my-select" v-model="quesAnswerTemp[0]">
                    <option selected :value="-1">--请选择--</option>
                    <option v-for="(item,index) in quesConfig.options" :value="index">{{item}}</option>
                </select>
            </div>
        </div>
        <div v-if="quesType==4" class="option-item">
            <!-- 填空题(暂时啥也没有) -->
        </div>
        <div v-if="quesType==5" class="option-item">
            <input type="text" class="my-input"  v-model="quesAnswerTemp[0]"  :placeholder="quesConfig.placeholder" />
        </div>
        <div v-if="quesType==6" class="option-item">
            <div class="my-textarea">
                <textarea  class="my-input"   v-model="quesAnswerTemp[0]"  :placeholder="quesConfig.placeholder"  onKeyPress="if(window.event.keyCode==13)this.blur()"></textarea>
            </div>
        </div>
        <div v-if="quesType==7" class="option-item ">
            <table class="matrix-table">
                <tr>
                    <th></th>
                    <th  v-for="(citem,cindex) in quesConfig.options" >{{citem}}</th>
                </tr>
                <tr  v-for="(item,index) in quesConfig.questions">
                    <td class="do-title">{{item}}</td>
                    <td  v-for="(citem,cindex) in quesConfig.options">
                        <div class="my-radio">
                            <input  type="radio" :name="index+timestamp" :id="getByIds(index+'_'+cindex)"  v-model="quesAnswerTemp[index]" :value="cindex"  />
                            <label :for="getByIds(index+'_'+cindex)"></label>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <div v-if="quesType==8" class="option-item">
            <table class="matrix-table">
                <tr>
                    <th></th>
                    <th  v-for="(citem,cindex) in quesConfig.options" >{{citem}}</th>
                </tr>
                <tr  v-for="(item,index) in quesConfig.questions">
                    <td class="do-title">{{item}}</td>
                    <td  v-for="(citem,cindex) in quesConfig.options">
                        <div class="my-checkbox"  >
                        <input type="checkbox" v-model="quesAnswerTemp[index]" :value="cindex" :name="index+timestamp" :id="getByIds(index+'_'+cindex)"  />
                            <label :for="getByIds(index+'_'+cindex)"></label>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <div v-if="quesType==9" class="option-item clearfix">
            <ques-range v-model="quesAnswerTemp[0]" :rangeNum="quesConfig.rangeNum"  :rangeType="quesConfig.rangeType"></ques-range>
        </div>
        <div v-if="quesType==10" class="option-item matrix-range">
            <table class="matrix-table">
                <tbody class="matrix-pc" >
                    <tr  v-for="(item,index) in quesConfig.questions">
                        <td class="do-title">{{item}}</td>
                        <td class=" clearfix">
                            <ques-range v-model="quesAnswerTemp[index]" :rangeNum="quesConfig.rangeNum"  :rangeType="quesConfig.rangeType"></ques-range>
                        </td>
                    </tr>
                </tbody>
                <tbody class="matrix-mobile" v-for="(item,index) in quesConfig.questions">
                    <tr>
                        <th style="text-align: left;">{{item}}</th>
                    </tr>
                    <tr class=" clearfix">
                        <ques-range v-model="quesAnswerTemp[index]" :rangeNum="quesConfig.rangeNum"  :rangeType="quesConfig.rangeType"></ques-range>
                    </tr>
                </tbody>
            </table>
        </div>
         <div v-if="quesType==11" class="option-item matrix-range">
             <div class="ques-sort-wrap">
                <div  :class="['sort-num',quesAnswerTemp.length>0?'acitve':'']" >
                <div  class="sort-num-item" v-for="(item,index) in quesConfig.options">{{index+1}}</div>
                </div>
                <draggable
                    class="sort-list"
                    element="ul"
                    v-model="quesSortTemp"
                    :options="sortDragOpt"
                    @end="move"
                >
                    <li v-for="(item,index) in quesSortTemp">
                        {{item.options}}
                        <i class="iconfont icon-tuodong"></i>
                    </li>
                 </draggable>
             </div>
        </div>
        <div v-if="quesType==12" class="option-item matrix-range">
            <ques-table v-model="quesAnswerTemp" :options="quesConfig.options" ></ques-table>
        </div>
        <div v-if="quesType==13" class="option-item">
            <ques-date v-model="quesAnswerTemp[0]" :dateType="quesConfig.dateType" :placeholder="quesConfig.placeholder" ></ques-date>
        </div>
    </div>
</template>

<script>
import editInput from "@/components/contentEdiInput.vue";
import quesRange from "@/components/quesRange.vue";
import draggable from "vuedraggable";
import quesTable from "@/components/quesTable.vue";
import quesDate from "@/components/quesDate.vue";
export default {
    components: { editInput,quesRange,draggable,quesTable,quesDate },
    name: "ques",
    props: {
        quesType: Number,
        quesConfig:Object,
        quesAnswer:{
            type:Array,
            default:[],
        },
    },
    model:{
        prop: 'quesAnswer',
        event:'answer'
    },
    data() {
        return {
            timestamp: new Date().getTime()+ '_' +  Math.floor(Math.random()*1000),
            // quesAnswerTemp:this.quesType==3?JSON.parse(JSON.parse(JSON.stringify(this.quesAnswer))):JSON.parse(JSON.stringify(this.quesAnswer)),
            quesAnswerTemp:JSON.parse(JSON.stringify(this.quesAnswer)),

           quesSortTemp:[],
        };
    },
    watch:{
        quesAnswerTemp(currVal){
            if(currVal)  this.$emit('answer',currVal)
        },
    },
    computed:{
        sortDragOpt() {
            return {
                animation: 150,
                group:{
                    name:"optlist",
                }, 
                chosenClass:'active',
                scroll: true,
                scrollSensitivity: 20
            };
        },
    },
    created(){
        const _this=this;
        // if(_this.quesType==3&&typeof _this.quesAnswerTemp=="string"){
        //     _this.quesAnswerTemp=JSON.parse(_this.quesAnswerTemp);
        // }
        console.log(typeof _this.quesAnswerTemp);
                if(_this.quesType==3  ){
           if( typeof  _this.quesAnswerTemp === 'string'){
                this.quesAnswerTemp=JSON.parse(this.quesAnswerTemp);
           }
           if(_this.quesAnswerTemp == -1){
               _this.quesAnswerTemp=[-1];
           }
        }
            
         if(_this.quesType==11){
            let arr=[],list=_this.quesConfig.options,answer=_this.quesAnswerTemp;
            if(answer.length==0){
                list.forEach((element,i) => {
                    arr.push({options:element,order:i});
                });
            }
            else{
                answer.forEach(element => {
                    let options=list[element];
                    arr.push({options,order:element});
                });
            }
            _this.quesSortTemp=arr;
         }
        
    },
    methods: {
        getByIds(index) {
            return  this.timestamp + "_" + (index + 1);
        },
        move(evt){
            let arr=[];
            this.quesSortTemp.forEach(element => {
               arr.push(element.order);
            });
            this.quesAnswerTemp=arr; 
        },
    }
};
</script>
<style lang="css" >

</style>