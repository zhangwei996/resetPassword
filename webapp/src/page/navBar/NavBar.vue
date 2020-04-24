<template>
  <div id="nav-bar">
    <div class="left">
      <slot name="left"></slot>
    </div>
    <div class="middle">
      <slot name="middle"></slot>
    </div>
    <div class="right">
      <slot name="right"></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavBar"
};
</script>

<style scoped>
#nav-bar {
  display: flex;
  line-height: 44px;
  height: 44px;
  text-align: center;
  box-shadow: 0 1px 1px rgba(100, 100, 100, 0.08);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 9999;
  background-color: #fff;
}

.left,
.right {
  width: 60px;
}
.middle {
  flex: 1;
}
.middle span {
  color: #000000;
  font-size: 15px;
}
.right {
  font-size: 12px;
}
</style>
