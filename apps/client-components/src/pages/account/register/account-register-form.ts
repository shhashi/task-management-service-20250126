import '@mdi/font/css/materialdesignicons.css';
import { createVuetifyApp } from '@/createAppWithVuetify';
import AccountRegisterPage from '@/pages/account/register/AccountRegisterPage.vue';

createVuetifyApp(AccountRegisterPage).mount('#app');
