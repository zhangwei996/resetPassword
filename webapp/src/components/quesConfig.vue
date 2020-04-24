<template>
  <div>
    <div class="config-item" v-if="[1,2,3].includes(currentQues.type)">
      <label for>选项管理</label>
      <ul class="config-options">
        <draggable class="ques-list" element="div" v-model="currentQuesConf.options" :options="optDragOpt"
          handle=".icon-tuodong">
          <li v-for="(item,index) in currentQuesConf.options">
            <input type="text" class="my-input" v-model="currentQuesConf.options[index]" />
            <i class="iconfont icon-tuodong"></i>
            <i class="iconfont icon-input-remove" @click="delOptions(index)"></i>
          </li>
        </draggable>
        <li class="mb10">
          <a href="javascript:;" @click="addOptions">添加新选项</a>
        </li>
      </ul>
    </div>
    <div v-else-if="[5,6].includes(currentQues.type)">
      <div class="config-item">
        <label for>输入提示</label>
        <input type="text" class="my-input" v-model="currentQuesConf.placeholder" />
      </div>
    </div>
    <div v-else-if="[7,8].includes(currentQues.type)">
      <div class="config-item">
        <label for>问题管理</label>
        <ul class="config-options">
          <draggable class="ques-list" element="div" v-model="currentQuesConf.questions" :options="optDragOpt"
            handle=".icon-tuodong">
            <li v-for="(item,index) in currentQuesConf.questions">
              <input type="text" class="my-input" v-model="currentQuesConf.questions[index]" />
              <i class="iconfont icon-tuodong"></i>
              <i class="iconfont icon-input-remove" @click="delMatrixOptions(currentQuesConf.questions,index)"></i>
            </li>
          </draggable>
          <li class="mb10">
            <a href="javascript:;" @click="addQuestions">添加新问题</a>
          </li>
        </ul>
      </div>
      <div class="config-item">
        <label for>选项管理</label>
        <ul class="config-options">
          <draggable class="ques-list" element="div" v-model="currentQuesConf.options" :options="optDragOpt"
            handle=".icon-tuodong">
            <li v-for="(item,index) in currentQuesConf.options">
              <input type="text" class="my-input" v-model="currentQuesConf.options[index]" />
              <i class="iconfont icon-tuodong"></i>
              <i class="iconfont icon-input-remove" @click="delMatrixOptions(currentQuesConf.options,index)"></i>
            </li>
          </draggable>
          <li class="mb10">
            <a href="javascript:;" @click="addOptions">添加新选项</a>
          </li>
        </ul>
      </div>
    </div>
    <div v-else-if="currentQues.type==9 || currentQues.type==10">
      <div class="config-item" v-if="currentQues.type==10">
        <label for>问题管理</label>
        <ul class="config-options">
          <draggable class="ques-list" element="div" v-model="currentQuesConf.questions" :options="optDragOpt"
            handle=".icon-tuodong">
            <li v-for="(item,index) in currentQuesConf.questions">
              <input type="text" class="my-input" v-model="currentQuesConf.questions[index]" />
              <i class="iconfont icon-tuodong"></i>
              <i class="iconfont icon-input-remove" @click="delMatrixOptions(currentQuesConf.questions,index)"></i>
            </li>
          </draggable>
          <li class="mb10">
            <a href="javascript:;" @click="addQuestions">添加新问题</a>
          </li>
        </ul>
      </div>
      <div class="config-item">
        <label for>量表类型</label>
        <select v-model="currentQuesConf.rangeType" class="my-select mb10">
          <option selected value="满意">满意度</option>
          <option value="认同">认同度</option>
          <option value="重要">重要度</option>
          <option value="愿意">愿意度</option>
          <option value="符合">符合度</option>
        </select>
      </div>
      <div class="config-item">
        <label for>量表范围</label>
        <input type="number" min="2" max="100" class="my-input" style="padding-right:0"
          onkeypress='return( /[\d]/.test(String.fromCharCode(event.keyCode)))' oninput="if(value>100)value=100"
          placeholder="5" v-model="currentQuesConf.rangeNum" />
      </div>
    </div>
    <div class="config-item" v-else-if="currentQues.type==11">
      <label for>选项管理</label>
      <ul class="config-options">
        <draggable class="ques-list" element="div" v-model="currentQuesConf.options" :options="optDragOpt"
          handle=".icon-tuodong">
          <li v-for="(item,index) in currentQuesConf.options">
            <input type="text" class="my-input" v-model="currentQuesConf.options[index]" />
            <i class="iconfont icon-tuodong"></i>
            <i class="iconfont icon-input-remove" @click="delOptions(index)"></i>
          </li>
        </draggable>
        <li class="mb10">
          <a href="javascript:;" @click="addOptions">添加新选项</a>
        </li>
      </ul>
    </div>
    <div class="config-item" v-if="[12].includes(currentQues.type)">
      <label for>表头管理</label>
      <ul class="config-options">
        <draggable class="ques-list" element="div" v-model="currentQuesConf.options" :options="optDragOpt"
          handle=".icon-tuodong">
          <li v-for="(item,index) in currentQuesConf.options">
            <input type="text" class="my-input" v-model="currentQuesConf.options[index]" />
            <i class="iconfont icon-tuodong"></i>
            <i class="iconfont icon-input-remove" @click="delOptions(index)"></i>
          </li>
        </draggable>
        <li class="mb10">
          <a href="javascript:;" @click="addOptions">添加表头选项</a>
        </li>
      </ul>
    </div>
    <div v-else-if="currentQues.type==13">
      <div class="config-item">
        <label for>时间类型</label>
        <select v-model="currentQuesConf.dateType" @change="dateSelChange" class="my-select mb10" style="width:100%">
          <option selected :value="1">仅日期</option>
          <option :value="2">仅时间</option>
          <option :value="3">日期+时间</option>
        </select>
      </div>
      <div class="config-item">
        <label for>输入提示</label>
        <input type="text" class="my-input" v-model="currentQuesConf.placeholder" />
      </div>
      <!-- <div class="config-item">
                <label for>时间范围</label><br>
                <DatePicker v-if="currentQuesConf.dateType==1" type="daterange" v-model="currentQuesConf.questions" placeholder="请选择时间间隔"  transfer style="width:100%"></DatePicker>
                <TimePicker v-if="currentQuesConf.dateType==2" type="timerange" v-model="currentQuesConf.questions" placeholder="请选择时间间隔"  transfer style="width:100%"></TimePicker >
                <DatePicker v-if="currentQuesConf.dateType==3" type="datetimerange" v-model="currentQuesConf.questions" placeholder="请选择时间间隔"  transfer style="width:100%"></DatePicker>
            </div> -->
    </div>

  </div>
</template>

<script>
  import draggable from "vuedraggable";
  export default {
    components: {
      draggable
    },
    props: {
      currentQues: Object,
    },
    model: {
      prop: 'currentQues',
      event: 'quesConfig',
    },
    data() {
      return {
        currentIndex: 0,
      }
    },
    computed: {
      optDragOpt() {
        return {
          animation: 150,
          group: "options",
          ghostClass: "option-ghost",
          scroll: true,
          scrollSensitivity: 20
        };
      },
      currentQuesConf() {
        return this.currentQues.config || null;
      },
    },
    methods: {
      //添加选项
      addOptions() {
        const _this = this;
        var optlist = _this.currentQuesConf.options;
        optlist.push("选项" + (optlist.length + 1));
      },
      //删除选项
      delOptions(_index) {
        const _this = this;
        var optlist = _this.currentQuesConf.options;
        optlist.splice(_index, 1);
      },
      //添加问题
      addQuestions() {
        const _this = this;
        var queslist = _this.currentQuesConf.questions;
        queslist.push("问题" + (queslist.length + 1));
        if (this.currentQues.type == 8)
          _this.currentQues.answer.push([]);

      },
      //删除矩阵问题
      delMatrixOptions(_arr, _index) {
        _arr.splice(_index, 1);
        if (this.currentQues.type == 8)
          this.currentQues.answer.splice(_index, 1);
      },
      dateSelChange() {
        console.log(233);

        this.currentQuesConf.placeholder = this.currentQuesConf.dateType == 2 ? '请选择时间' : '请选择日期';
      }
    }
  }

</script>

<style lang="scss">
  .config-content {
    padding: 10px 0;
    position: relative;
  }

  .config-item {
    label {
      font-size: 14px;
      color: #666;
      line-height: 34px;
    }

    input {
      margin-bottom: 10px;
    }

    border-bottom: 1px solid #e0e0e0;
    margin: 0 10px 18px;

    .config-options {
      li {
        overflow: hidden;
        display: flex;
      }

      .my-input {
        flex: 1;
        float: left;
      }

      i {
        flex-basis: 18px;
        float: left;
        line-height: 32px;
        height: 32px;
        color: #596775;
        text-align: center;
        margin: 0 10px;
      }

      .icon-tuodong {
        cursor: move;
      }

      .icon-input-remove {
        margin: 0;
        cursor: pointer;

        &:hover {
          color: #f56c6c;
        }
      }

      a {
        color: #409eff;
        outline: none;
        font-size: 12px;

        &:hover {
          color: #5faaf8;
        }
      }
    }
  }

</style>
