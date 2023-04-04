<template>
  <div class="chat">
    <div class="chat-history">
      <div v-for="(message, index) in messages" :key="index" class="message" :class="{ 'from-me': message.fromMe }">
        <div class="message-content">
          {{ message.text }}
        </div>
      </div>
    </div>
    <div class="chat-input">
      <input type="text" v-model="newMessage" @keydown.enter="sendMessage" placeholder="Type a message...">
      <button @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<script>
import {HttpManager} from "../api";
import {message} from "../../test/e2e/custom-assertions/elementCount";

export default {
  data() {
    return {
      messages: [
        { text: 'Hi there, I am your chatbot! How can I assist you today?', fromMe: false }
      ],
      newMessage: ''
    }
  },
  methods: {
    sendMessage() {
      if (this.newMessage) {
        this.messages.push({ text: this.newMessage, fromMe: true })
        this.getBotReply(this.newMessage)
        this.newMessage = ''
      }
    },
    getBotReply(question) {
      HttpManager.getBotReply(question).then(res => {
        console.log(res.reply)
        this.messages.push({ text: res.reply, fromMe: false })
      })
        .catch(err => {
          console.log(err)
        })
    },
  }
}
</script>

<style>
.chat {
  width: 100%;
  height: 800px;
  margin: auto;
  padding-top: 100px;
}
.chat-history {
  flex-grow: 1;
  overflow-y: scroll;
}
.message {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}
.message-content {
  display: inline-block;
  padding: 8px;
  border-radius: 10px;
  background-color: #eee;
}
.from-me .message-content {
  background-color: #0084ff;
  color: #fff;
}
.chat-input {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}
.chat-input input[type="text"] {
  flex-grow: 1;
  margin-right: 10px;
  padding: 8px;
  border-radius: 10px;
  border: none;
  background-color: #eee;
}
.chat-input button {
  padding: 8px 16px;
  border-radius: 10px;
  border: none;
  background-color: #0084ff;
  color: #fff;
}
</style>
