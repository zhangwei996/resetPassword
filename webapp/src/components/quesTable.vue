<template>
  <div>
      <table class="table">
            <tr>
                <th class="tc"  v-for="(citem,cindex) in options" >{{citem}}</th>
                <a href="javascript:;" class="link-file" @click="selectFile">批量导入</a>
            </tr>
            <tr  v-for="(item,index) in quesAnswer">
                <td  v-for="(citem,cindex) in options">
                    <input  type="text" v-model="quesAnswer[index][cindex]">
                </td>
                <button class="remove-tr-btn" @click="removeOpt(index)"><i class="iconfont icon-input-remove"></i></button>
            </tr>
        </table>
        <!-- {{quesAnswer}} -->
        <button class="add-tr-btn" @click="addOpt"><i class="iconfont icon-addgrey"></i></button>
        <input type="file" :id="timestamp" @change="fileChange" style="display:none;" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
  </div>
</template>

<script>
import XLSX from 'xlsx'
export default {
    props:{
        quesAnswer:{
            type:Array,
            default:()=>[],
        },
        options:Array,
    },
    model:{
        prop:"quesAnswer",
        event:'awser',
    },
    data(){
        return  {
            timestamp: new Date().getTime()+'_'+  Math.floor(Math.random()*1000),
        }
    },
    watch:{
        quesAnswer(currVal){
            console.log(currVal);
            this.$emit('awser',currVal);
            console.log(currVal);
        },
    },
    created(){
        if(JSON.stringify(this.quesAnswer)==='[]')
            this.addOpt();
    },
    methods:{
        addOpt(){
            const _this=this;
            var arr=[];
            this.options.forEach(element => {
                 arr.push('');
            });
            this.quesAnswer.push(arr);
        },
        removeOpt(index){
            const _this=this;
            this.quesAnswer.splice(index,1);
        },
        selectFile(){
            document.getElementById(this.timestamp).click();
        },
        fileChange(e){
            const _this=this;
            var files = e.target.files;
			if(files.length == 0) return;
			var f = files[0];
			if(!/\.xlsx$/g.test(f.name)) {
				alert('仅支持读取xlsx格式！');
				return;
			}
			this.readWorkbookFromLocalFile(f, function(workbook) {
                var json=XLSX.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[0]]);
                let arr=[];
                json.forEach(element => {
                    let childArr=[];
                    for (const key in element) {
                        const celement = element[key];
                        childArr.push(celement);
                    }
                     arr.push(childArr);
                });
                _this.quesAnswer=arr;
			});
        },
        readWorkbookFromLocalFile(file, callback) {
            var reader = new FileReader();
            reader.onload = function(e) {
                var data = e.target.result;
                var workbook = XLSX.read(data, {type: 'binary'});
                if(callback) callback(workbook);
            };
            reader.readAsBinaryString(file);
        }
    },
}
</script>

<style lang="scss" scoped>
.tc{text-align: center !important;}
table{
    input{
        outline: none;
        width:100%;
        background-color: transparent;
    }
}
    .add-tr-btn{
        padding: 5px 10px;
        border: 1px solid #e5e6e4;
        display: block;
        margin: 12px auto;
        color: #666;
        border-radius: 3px;
        background: transparent;
        outline: none;
    }
    .remove-tr-btn{
        display: block;
        margin: 12px 0 12px 8px;
        color: #666;
        border-radius: 3px;
        background: transparent;
        outline: none;
    }
    .link-file{
        font-size: 12px;
        width:30px;
        display: block;
        margin:3px 0 0 12px;
    }
    @media screen and (max-width: 768px){
        .link-file{
            display: none
        }
    }
</style>