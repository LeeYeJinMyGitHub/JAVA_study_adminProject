<template>
    <v-app>
        <v-data-table
            :headers="tableHeaders"
            :items="tableItems"
            :items-per-page="5"
            :search="search"
            :sort-by="['id']"
            :sort-desc="[true]"
            @click:row="getBoardDetail">
            <template v-slot>
                <v-text-field
                    v-model="search"
                    label="검색"
                    placeholder="검색어를 입력하세요"
                    filled="filled"
                    rounded="rounded"
                    dense="dense"
                    clearable="clearable"></v-text-field>
            </template>
        </v-data-table>
        <modal v-if="showModal" @close="showModal = false">
            <h3 slot="header">상세페이지</h3>
            <span slot="body">
                <v-simple-table>
                    <template v-slot:default>
                        <thead>
                            <tr>
                                <th class="text-center" width="20%">
                                    제목
                                </th>
                                <th class="text-center" width="70%">
                                    내용
                                </th>
                                <th class="text-center" width="10%">
                                    등록일
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>{{ boardDetail.title }}</td>
                                <td>{{ boardDetail.contents }}</td>
                                <td>{{ boardDetail.created_at }}</td>
                            </tr>
                        </tbody>
                    </template>
                </v-simple-table>
            </span>
            <v-btn
                depressed="depressed"
                slot="footer"
                @click="showModal = false"
                aria-hidden="true">닫기</v-btn>
        </modal>

        <!-- <div v-if="posts.length > 0"> <table> <thead> <tr> <th>번호</th> <th>제목</th>
        <th>내용</th> </tr> </thead> <tbody> <tr v-for="post in posts" :key="post.id">
        <td> {{ post.id }} </td> <td>{{ post.title }}</td> <td>{{ post.contents }}</td>
        </tr> </tbody> </table> </div> <div v-else> <p>데이터가 없습니다.</p> </div> -->
    </v-app>
</template>

<script>
    import axios from 'axios';
    import Modal from './ModalComponent.vue'

    export default {
        name: "BoardList",
        data() {
            return {
                //posts: [],
                search: '',
                tableHeaders: [
                    {
                        text: '번호',
                        value: 'id',
                        width: '10%',
                        align: 'center'
                    }, {
                        text: '제목',
                        value: 'title',
                        width: '15%',
                        align: 'center'
                    }, {
                        text: '내용',
                        value: 'contents',
                        width: '65%',
                        align: 'center'
                    }, {
                        text: '등록일',
                        value: 'created_at',
                        width: '10%',
                        align: 'center'
                    }
                ],
                tableItems: [],
                boardDetail: [],
                showModal: false
            };
        },
        mounted() {
            this.getBoardList();
        },
        methods: {
            getBoardList() {
                var vm = this;
                axios
                    .get('http://localhost:8080/list/getBoardList')
                    .then(function (response) {
                        vm.tableItems = response.data;
                        //vm.posts = response.data;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            getBoardDetail(row) {
                var vm = this;
                const url = "http://localhost:8080/list/getBoardDetail/" + row.id;
                axios
                    .post(url)
                    .then(function (response) {
                        vm.boardDetail = response.data;
                        vm.showModal = !vm.showModal;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        components: {
            Modal: Modal
        }
    }
</script>

<style scoped="scoped">
    #app {
        height: 500px;
    }
    * {
        text-align: center;
    }
</style>