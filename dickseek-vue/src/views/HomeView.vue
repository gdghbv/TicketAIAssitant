<template>
    <div class="home">
      <h1>订票助手 AI</h1>
      <p class="subtitle">我是订票系统专属助手，可以回答关于飞机订票、购票和旅游出行的问题</p>
      <ChatWindow />
    </div>
  </template>
  
  <script setup>
  import ChatWindow from '@/components/ChatWindow.vue'
  </script>
  
  <style scoped>
  .home {
    max-width: 900px;
    margin: 0 auto;
    padding: 20px;
  }
  
  .subtitle {
    color: #666;
    margin-bottom: 30px;
  }
  </style>