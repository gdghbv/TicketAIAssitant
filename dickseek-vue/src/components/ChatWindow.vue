<template>
    <div class="chat-container">
      <div class="messages">
        <div 
          v-for="(message, index) in messages" 
          :key="index" 
          :class="['message', message.role]"
        >
          <div class="message-content" style="white-space: pre-wrap;">{{ message.content }}</div>
        </div>
        <div v-if="isLoading" class="message assistant">
          <div class="message-content">思考中...</div>
        </div>
      </div>
      
      <form @submit.prevent="handleSubmit" class="input-area">
        <input
          v-model="inputMessage"
          type="text"
          placeholder="输入您的订票相关问题..."
          :disabled="isLoading"
        />
        <button type="submit" :disabled="isLoading || !inputMessage.trim()">
        发送
        </button>
      </form>
      
      <div v-if="error" class="error-message">{{ error }}</div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import { useChatStore } from '@/stores/chat'
  import { storeToRefs } from 'pinia'
  
  const chatStore = useChatStore()
  const inputMessage = ref('')

  
  const { messages,  isLoading,error } = storeToRefs(chatStore)
  
  const handleSubmit = async() => {
    if (inputMessage.value.trim()) {
      await chatStore.askQuestion(inputMessage.value)
      inputMessage.value = ''
    }
  }
  </script>
  
  <style scoped>
  .chat-container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
  }
  
  .messages {
    margin-bottom: 20px;
    min-height: 300px;
    max-height: 500px;
    overflow-y: auto;
    padding: 10px;
    background: #f9f9f9;
    border-radius: 4px;
  }
  
  .message {
    margin-bottom: 15px;
    padding: 10px;
    border-radius: 4px;
  }
  
  .message.user {
    background: #e3f2fd;
    margin-left: 20%;
    text-align: right;
  }
  
  .message.assistant {
    background: #f5f5f5;
    margin-right: 20%;
  }
  
  .input-area {
    display: flex;
    gap: 10px;
  }
  
  .input-area input {
    flex-grow: 1;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  
  .input-area button {
    padding: 10px 20px;
    background: #42b983;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .input-area button:disabled {
    background: #ccc;
    cursor: not-allowed;
  }
  
  .error-message {
    color: #f44336;
    margin-top: 10px;
  }
  </style>