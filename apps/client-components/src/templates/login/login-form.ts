import '@mdi/font/css/materialdesignicons.css';
import LoginForm from '@/component/LoginForm.vue';
import { createVuetifyApp } from '@/createAppWithVuetify';

createVuetifyApp(LoginForm).mount('#app');
