<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="手机号" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入密码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否邀请" prop="isInvite">
        <el-select v-model="queryParams.isInvite" placeholder="请选择是否邀请" clearable>
          <el-option
            v-for="dict in dict.type.yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="注册日期">
        <el-date-picker
          v-model="daterangeRegisterTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="上次登录">
        <el-date-picker
          v-model="daterangeLastLoginTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="有效期">
        <el-date-picker
          v-model="daterangeVipTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="所属代理" prop="inviteCode">
        <el-input
          v-model="queryParams.inviteCode"
          placeholder="请输入所属代理"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="权限" prop="permissions">
        <el-select v-model="queryParams.permissions" placeholder="请选择权限" clearable>
          <el-option
            v-for="dict in dict.type.permission"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="付费金额" prop="moneys">
        <el-input
          v-model="queryParams.moneys"
          placeholder="请输入付费金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['gtos:users:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['gtos:users:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['gtos:users:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['gtos:users:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="usersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="编号" align="center" prop="id" />-->
      <el-table-column label="手机号" align="center" prop="phoneNumber" width="110%" sortable/>
      <el-table-column label="用户名" align="center" prop="userName"  sortable/>
<!--      <el-table-column label="密码" align="center" prop="password" />-->
      <el-table-column label="是否邀请" align="center" prop="isInvite" sortable>
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yes_no" :value="scope.row.isInvite"/>
        </template>
      </el-table-column>
      <el-table-column label="注册日期" align="center" prop="registerTime" width="180" sortable>
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registerTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上次登录" align="center" prop="lastLoginTime" width="180" sortable>
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastLoginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="有效期" align="center" prop="vipTime" width="180" sortable>
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.vipTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="所属代理" align="center" prop="inviteCode" sortable/>
      <el-table-column label="权限" align="center" prop="permissions" sortable>
        <template slot-scope="scope">
          <dict-tag :options="dict.type.permission" :value="scope.row.permissions"/>
        </template>
      </el-table-column>
      <el-table-column label="付费金额" align="center" prop="moneys" sortable>
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ `￥`+(scope.row.moneys/100).toFixed(2) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['gtos:users:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['gtos:users:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="手机号" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="是否邀请" prop="isInvite">
          <el-radio-group v-model="form.isInvite">
            <el-radio
              v-for="dict in dict.type.yes_no"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="注册日期" prop="registerTime">
          <el-date-picker clearable
            v-model="form.registerTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择注册日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上次登录" prop="lastLoginTime">
          <el-date-picker clearable
            v-model="form.lastLoginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上次登录时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="有效期" prop="vipTime">
          <el-date-picker clearable
            v-model="form.vipTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择有效期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="所属代理" prop="inviteCode">
          <el-input v-model="form.inviteCode" placeholder="请输入所属代理" />
        </el-form-item>
        <el-form-item label="权限" prop="permissions">
          <el-select v-model="form.permissions" placeholder="请选择权限">
            <el-option
              v-for="dict in dict.type.permission"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="付费金额" prop="moneys">
          <el-input v-model="form.moneys" placeholder="请输入付费金额" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUsers, getUsers, delUsers, addUsers, updateUsers } from "@/api/gtos/users";

export default {
  name: "Users",
  dicts: ['permission', 'yes_no'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户信息表格数据
      usersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 付费金额时间范围
      daterangeRegisterTime: [],
      // 付费金额时间范围
      daterangeLastLoginTime: [],
      // 付费金额时间范围
      daterangeVipTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        phoneNumber: null,
        userName: null,
        password: null,
        isInvite: null,
        registerTime: null,
        lastLoginTime: null,
        vipTime: null,
        inviteCode: null,
        permissions: null,
        moneys: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        phoneNumber: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        userName: [
          { required: true, message: "用户名不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
        isInvite: [
          { required: true, message: "是否邀请不能为空", trigger: "change" }
        ],
        permissions: [
          { required: true, message: "权限不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeRegisterTime && '' != this.daterangeRegisterTime) {
        this.queryParams.params["beginRegisterTime"] = this.daterangeRegisterTime[0];
        this.queryParams.params["endRegisterTime"] = this.daterangeRegisterTime[1];
      }
      if (null != this.daterangeLastLoginTime && '' != this.daterangeLastLoginTime) {
        this.queryParams.params["beginLastLoginTime"] = this.daterangeLastLoginTime[0];
        this.queryParams.params["endLastLoginTime"] = this.daterangeLastLoginTime[1];
      }
      if (null != this.daterangeVipTime && '' != this.daterangeVipTime) {
        this.queryParams.params["beginVipTime"] = this.daterangeVipTime[0];
        this.queryParams.params["endVipTime"] = this.daterangeVipTime[1];
      }
      listUsers(this.queryParams).then(response => {
        this.usersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        phoneNumber: null,
        userName: null,
        password: null,
        isInvite: null,
        registerTime: null,
        lastLoginTime: null,
        vipTime: null,
        inviteCode: null,
        permissions: null,
        moneys: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeRegisterTime = [];
      this.daterangeLastLoginTime = [];
      this.daterangeVipTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUsers(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUsers(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUsers(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除用户信息编号为"' + ids + '"的数据项？').then(function() {
        return delUsers(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('gtos/users/export', {
        ...this.queryParams
      }, `users_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
