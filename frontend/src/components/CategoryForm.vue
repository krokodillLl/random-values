<template>
  <form @submit.prevent="handleSubmit">
    <div class="container">
      <div class="row p-3 border">
        <div class="col">
          <input id="categoryName" class="form-control" type="text" v-model="category.name" required>
          <label class="form-label" for="categoryName">Category Name</label>
        </div>
        <div class="col">
          <input id="categoryDescription" class="form-control" type="text" v-model="category.description" required>
          <label class="form-label" for="categoryDescription">Category Description</label>
        </div>
        <div class="col"></div>
        <div class="col">
          <button class="btn btn-success btn-lg col-md-8" v-if="!isUpdating" type="submit">Create Category</button>
          <button class="btn btn-warning btn-lg col-md-8" v-else type="submit">Update Category</button>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
import apiClient from '../services/api';

export default {
  data() {
    return {
      category: {
        name: '',
        description: ''
      },
      isUpdating: false,
      categoryIdToUpdate: null
    };
  },
  methods: {
    handleSubmit() {
      if (this.isUpdating) {
        this.updateCategory();
      } else {
        this.createCategory();
      }
    },
    createCategory() {
      apiClient.post('/api/1/random/category/add', this.category)
          .then(response => {
            console.log('Category created:', response.data);
            this.$emit('refresh-categories');
            this.category = {name: '', description: ''};
          })
          .catch(error => {
            console.error('Error creating category:', error);
          });
    },
    updateCategory() {
      apiClient.put(`/api/1/random/category/update/${this.categoryIdToUpdate}`, this.category)
          .then(response => {
            console.log('Category updated:', response.data);
            this.$emit('refresh-categories');
            this.category = {name: '', description: ''};
            this.isUpdating = false;
            this.categoryIdToUpdate = null;
          })
          .catch(error => {
            console.error('Error updating category:', error);
          });
    },
    populateCategoryFormForUpdate(category) {
      this.category = {...category};
      this.isUpdating = true;
      this.categoryIdToUpdate = category.id;
    }
  }
};
</script>
