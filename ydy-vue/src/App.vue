<template>
    <div id="app">
        <router-view/>
    </div>
</template>
/* 窗口缩小到一定程度固定页面 */
<style scoped>
#app {
    height: 100%;
    min-width: 1300px;
    /* overflow: auto; */
}
</style>