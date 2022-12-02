/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
    // config.uiColor = '#AADC6E';
	
	// 服务端的文件上传接口
    config.filebrowserUploadUrl = "/ckEditorUpload";
    var pathName = window.document.location.pathname;

    // Define changes to default configuration here. For example:
    config.uiColor = '#d1ecf1';
    // config.width = 500; //宽度
    // config.height = 280; //高度
    config.height = 460; //高度
    
    config.entities = false;
};
