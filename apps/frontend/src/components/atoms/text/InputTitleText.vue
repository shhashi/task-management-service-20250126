<template>
  <div class="text-h4">{{ text }}</div>
</template>

<script setup lang="ts">
type Props = {
  text: string;
};
const { text } = defineProps<Props>();
</script>
