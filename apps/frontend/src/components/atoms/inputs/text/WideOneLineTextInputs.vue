<template>
  <v-text-field v-model="inputText" :type="textFieldType"></v-text-field>
</template>

<script setup lang="ts">
import { computed } from 'vue';

const inputText = defineModel<string | null>('inputText');

type Props = {
  isPassword?: boolean;
};
const { isPassword } = defineProps<Props>();

const textFieldType = computed(() => {
  return isPassword ? 'password' : undefined;
});
</script>
